package com.special.blockduce.member.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.special.blockduce.Response;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.domain.Salt;
import com.special.blockduce.member.dto.MemberForm;
import com.special.blockduce.member.dto.ProfileDto;
import com.special.blockduce.member.repository.MemberRepository;
import com.special.blockduce.utils.SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public String getAccessToken(String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=360325f103f39664cd6c418590ff659c");
            sb.append("&redirect_uri=http://localhost:3000/kakaologin");
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    public ProfileDto getUserInfo(String access_Token) {
        ProfileDto userInfo = new ProfileDto();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            System.out.println(access_Token);
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            JsonObject propertie = element.getAsJsonObject().get("properties").getAsJsonObject();

            String id = element.getAsJsonObject().get("id").getAsString();
            String name = propertie.getAsJsonObject().get("nickname").getAsString();
            String img = propertie.getAsJsonObject().get("profile_image").getAsString();
            String email = null;
            if (kakao_account.getAsJsonObject().get("email") != null) {
                email = kakao_account.getAsJsonObject().get("email").getAsString();
                userInfo = new ProfileDto(id,email,name,img);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public void join(MemberForm form) {

        String password = form.getPassword();
        String account = form.getAccount();
        String salt = SaltUtil.genSalt();

        Member member = Member.builder().
                email(form.getEmail()).
                name(form.getName()).
                password(SaltUtil.encodePassword(salt,password)).
                kid(form.getKid()).
                ismem(form.getIsmem()).
                nickname(form.getNickname()).
                intro(form.getIntro()).
                img(form.getImg()).
                account(SaltUtil.encodePassword(salt,account)).
                key(form.getKey()).
                build();

        System.out.println("member = " + member);
        System.out.println("member = " + member.getClass());
        System.out.println("member = " + member.getClass().getName());

        memberRepository.save(member);
    }

    @Transactional
    public MemberForm findByKid(ProfileDto userinfo) {

        Optional<Member> member = memberRepository.findOptionalByKid(userinfo.getId());

        MemberForm memf;
        if(member.isPresent()){ //계정이 있을 경우
            memf =  MemberForm.builder().
                    kid(userinfo.getId()).
                    email(userinfo.getEmail()).
                    name(userinfo.getName()).
                    ismem(member.get().getIsmem()).
                    build();
        }else{
            memf =  MemberForm.builder().
                    kid(userinfo.getId()).
                    email(userinfo.getEmail()).
                    name(userinfo.getName()).
                    build();
        }
        return memf;
    }



    public MemberForm findById(Long memberId, MemberForm form) {
// findById 사용 상황
// 1. 맴버 정보 조회 (form에 eth,dbc null로 전달) 2. id+eth or dbc 넣기
        Optional<Member> member = memberRepository.findOptionalById(memberId);

        MemberForm memf =new MemberForm();

        if(form.getEth()==null && form.getDbc()==null) {  //맴버 정보 조회하는경우
            if (member.isPresent()) { //계정이 있을 경우
                memf = MemberForm.builder().
                        img(member.get().getImg()).
                        name(member.get().getName()).
                        nickname(member.get().getNickname()).
                        intro(member.get().getIntro()).
                        wallet(member.get().getWallet()).
                        eth(member.get().getEth()).
                        dbc(member.get().getDbc()).
                        ismem(member.get().getIsmem()).
                        build();
            } else {
                memf = MemberForm.builder().ismem(false).build(); //계정 존재 여부 -> 플래그로 파악
            }
        }else if(form.getEth()!=null && form.getDbc()==null){ //eth 넣기

            if (member.isPresent()) { //계정이 있을 경우
                memf = MemberForm.builder().
                        eth(member.get().getEth()).
                        build();
            } else {
                memf = MemberForm.builder().ismem(false).build(); //계정 존재 여부 -> 플래그로 파악
            }
        }else if(form.getEth()==null && form.getDbc()!=null){//dbc 넣기
            if (member.isPresent()) { //계정이 있을 경우
                memf = MemberForm.builder().
                        dbc(member.get().getDbc()).
                        build();
            } else {
                memf = MemberForm.builder().ismem(false).build(); //계정 존재 여부 -> 플래그로 파악
            }
        }
        return memf;
    }
}
