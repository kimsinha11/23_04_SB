package com.KoreaIT.ksh.demo.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.ksh.demo.service.MemberService;
import com.KoreaIT.ksh.demo.util.Ut;
import com.KoreaIT.ksh.demo.vo.Article;
import com.KoreaIT.ksh.demo.vo.Member;
import com.KoreaIT.ksh.demo.vo.ResultData;
import com.KoreaIT.ksh.demo.vo.Rq;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private Rq rq;
	@RequestMapping("/usr/member/login")
	public String login() {

		
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {

		
		if (rq.isLogined()) {
			return Ut.jsHistoryBack("F-A", "이미 로그인 상태입니다.");
		}
		if (Ut.empty(loginId)) {
			return Ut.jsHistoryBack("F-3", "아이디를 입력해주세요.");
		}
		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("F-4", "비밀번호를 입력해주세요");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Ut.jsHistoryBack("F-3", "없는 아이디입니다");
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("F-4", "비밀번호가 틀렸습니다");
		}
		
		rq.login(member);

		return String.format("<script>alert('로그인 성공.'); location.replace('../article/list');</script>");
	}
	
	@RequestMapping("/usr/member/logout")
	@ResponseBody
	public String doLogout() {

		if(!rq.isLogined()) {
			return Ut.jsHistoryBack("F-1", "이미 로그아웃 상태입니다.");
		}
		
		rq.logout();

		return Ut.jsReplace("S-1", "로그아웃 되었습니다", "/");
	}
	
	@RequestMapping("/usr/member/join")
	public String join(HttpServletRequest req) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		return "usr/member/join";
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		if (Ut.empty(loginId)) {
			return Ut.jsHistoryBack("F-3", "아이디를 입력해주세요.");
		}
		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("F-3", "비밀번호를 입력해주세요.");
		}
		if (Ut.empty(name)) {
			return Ut.jsHistoryBack("F-3", "이름을 입력해주세요.");
		}
		if (Ut.empty(nickname)) {
			return Ut.jsHistoryBack("F-3", "닉네임을 입력해주세요.");
		}
		if (Ut.empty(cellphoneNum)) {
			return Ut.jsHistoryBack("F-3", "전화번호를 입력해주세요.");
		}
		if (Ut.empty(email)) {
			return Ut.jsHistoryBack("F-3", "이메일을 입력해주세요.");
		}

		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (joinRd.isFail()) {
			return Ut.jsHistoryBack("F-3", "이미 사용중인 아이디입니다");
		}

		Member member = memberService.getMemberById(joinRd.getData1());

		return String.format("<script>alert('회원가입 성공.'); location.replace('../article/list');</script>");
	}

	@RequestMapping("/usr/member/profile")
	public String profile(Model model, int id) {
		id = rq.getLoginedMemberId();
		Member member = memberService.profile(id);

		if (member == null) {
			return rq.jsHistoryBackOnView(Ut.f("%d번 글은 존재하지 않습니다", id));
		}

		model.addAttribute("member", member);
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId());

		return "usr/member/profile";
	}

}