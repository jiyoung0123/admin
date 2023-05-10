package com.kbstar.controller;

import com.kbstar.dto.Adm;
import com.kbstar.dto.Cust;
import com.kbstar.dto.Item;
import com.kbstar.service.AdmService;
import com.kbstar.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {
    @Value("${adminserver}")
    String adminserver;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    CustService custService;
    @Autowired
    AdmService admService;
    @RequestMapping("/")
    public String main(Model model){
        model.addAttribute("adminserver",adminserver);
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("center","login");
        return "index";
    }

    @RequestMapping("/loginimpl")
    public String loginimpl(Model model, String id, String pwd, HttpSession session) throws Exception {
        Adm adm = null;
        String nextPage ="loginfail";

        try {
            adm = admService.get(id);
            //&&가 2개면, 앞에꺼 실패하면 뒤에껀 안 함
            if(adm != null && encoder.matches(pwd, adm.getPwd())){
                nextPage ="loginok";
                //session에 담은것도 jsp파일에서 $ 로 꺼낼 수 있다
                session.setMaxInactiveInterval(100000);
                session.setAttribute("loginadm", adm);
            }
        } catch (Exception e) {
            //throw new Exception("시스템장애 잠시후 다시 로그인 하세요!");
            e.printStackTrace();

        }
        model.addAttribute("center", nextPage);
        return "index";
    }




    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("center","register");
        return "index";
    }

    @RequestMapping("/registerimpl")
    public String registerimpl(Model model, Adm adm, HttpSession session) throws Exception {
        try {
            adm.setPwd(encoder.encode(adm.getPwd()));
            admService.register(adm);
            session.setAttribute("loginadm", adm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("가입 오류");
        }
        model.addAttribute("radm", adm);
       // model.addAttribute("center", "registerok");
        return "redirect:/";
    }

    @RequestMapping("/logoutimpl")
    public String logoutimpl(Model model, HttpSession session){
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }

    @RequestMapping("/register1")
    public String register1(Model model){
        model.addAttribute("center","register1");
        return "index";
    }

    @RequestMapping("/login1")
    public String login1(Model model){
        model.addAttribute("center","login1");
        return "index";
    }
    @RequestMapping("/charts")
    public String chart(Model model){
        model.addAttribute("center","charts");
        return "index";
    }

    @RequestMapping("/websocket")
    public String websocket(Model model){
        model.addAttribute("adminserver",adminserver);
        model.addAttribute("center","websocket");
        return "index";
    }

    @RequestMapping("/logouts")
    public String logout(Model model, HttpSession session){
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }


//    @RequestMapping("/tables")
//    public String tables(Model model){
//        List <Item> list = new ArrayList<>();
//        list.add(new Item(0,"바지",10, new Date(),1000));
//        list.add(new Item("james2","manager","b.jpg",20, new Date(),2000));
//        list.add(new Item("james3","manager","c.jpg",30, new Date(),3000));
//        list.add(new Item("james4","manager","d.jpg",40, new Date(),1000));
//        list.add(new Item("james5","manager","e.jpg",50, new Date(),1000));
//        list.add(new Item("james6","manager","f.jpg",60, new Date(),1000));
//        list.add(new Item("james7","manager","g.jpg",70, new Date(),1000));
//        list.add(new Item("james8","manager","h.jpg",80, new Date(),1000));
//        list.add(new Item("james9","manager","i.jpg",90, new Date(),1000));
//        list.add(new Item("james10","manager","j.jpg",100, new Date(),1000));
//        list.add(new Item("james12","manager","k.jpg",10, new Date(),1000));
//        list.add(new Item("james12","manager","l.jpg",20, new Date(),2000));
//        list.add(new Item("james13","manager","m.jpg",30, new Date(),3000));
//        list.add(new Item("james14","manager","n.jpg",40, new Date(),1000));
//        list.add(new Item("james15","manager","o.jpg",50, new Date(),1000));
//        list.add(new Item("james16","manager","p.jpg",60, new Date(),1000));
//        list.add(new Item("james17","manager","q.jpg",70, new Date(),1000));
//        list.add(new Item("james18","manager","r.jpg",80, new Date(),1000));
//        list.add(new Item("james19","manager","s.jpg",90, new Date(),1000));
//        list.add(new Item("james20","manager","t.jpg",100, new Date(),1000));
//        model.addAttribute("members", list);
//        model.addAttribute("center","tables");
//        return "index";
//    }

//    List<Item> list = new ArrayList<>();
//        list.add(new Item(100,"pants1",1000,"a.jpg",new Date()));
//        list.add(new Item(101,"pants2",2000,"b.jpg",new Date()));
//        list.add(new Item(102,"pants3",3000,"c.jpg",new Date()));
//        list.add(new Item(103,"pants4",4000,"d.jpg",new Date()));
//        list.add(new Item(104,"pants5",5000,"e.jpg",new Date()));
//
//        model.addAttribute("allitem", list);
//
//        model.addAttribute("left",dir+"left");
//        model.addAttribute("center",dir+"all");
//        return "index";






    @RequestMapping("/color")
    public String color(Model model){
        model.addAttribute("center","color");
        return "index";
    }

    @RequestMapping("/livechart")
    public String livechart(Model model){
        model.addAttribute("adminserver",adminserver);
        model.addAttribute("center","livechart");
        return "index";
    }
}


