package com.stayc.infra.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stayc.common.constants.Constants;
import com.stayc.common.util.UtilFunction;
import com.stayc.infra.member.MemberDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChatController {
	
	
	@Autowired
	ChatService service;
	
	@RequestMapping(value = "/chatroom")
	public String chatroom(@ModelAttribute("vo") ChatVo vo,ChatDto dto ,Model model,HttpSession httpSession) throws Exception {
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();         
		String nowtime = date.format(now);
		
		
			
		LocalTime now2 = LocalTime.now(); // 현재 시간을 가져옴
	    System.out.println("현재 시간: " + now2); 
	        
	        // 한 시간을 뺀 시간을 계산
	        LocalTime oneHourEarlier = now2.minusHours(1);
	        System.out.println("한 시간 전: " + oneHourEarlier);
	        
	        // DateTimeFormatter를 사용하여 시간을 "HH" 형식으로 포맷
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");        
	        String formatedNow = oneHourEarlier.format(formatter);
	        
	        System.out.println(formatedNow + "--------------------------");
		  
		  
		 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");
         urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=N3YEBL3S%2BptRSuZYd5A3x7XK3VTIf61bowQ48lqXUISpn3BNT64x0ZAv4fEz36B06RS%2FTml5T6otfwpL9jre%2FQ%3D%3D");
         urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
         urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8"));
         urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
         urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(nowtime, "UTF-8"));
         urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(formatedNow+"00", "UTF-8"));
         urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode("60", "UTF-8"));
         urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode("127", "UTF-8"));
         
         

         URL url = new URL(urlBuilder.toString());
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         conn.setRequestProperty("Content-type", "application/json");
         System.out.println("Response code: " + conn.getResponseCode());

         BufferedReader rd;
         if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
             rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         } else {
             rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
         }
         StringBuilder sb = new StringBuilder();
         String line;
         while ((line = rd.readLine()) != null) {
             sb.append(line);
         }
         rd.close();
         conn.disconnect();

//         ObjectMapper objectMapper2 = new ObjectMapper();
//         JsonNode node = objectMapper2.readTree(sb.toString());
         
         ObjectMapper objectMapper2 = new ObjectMapper();
         JsonNode node = objectMapper2.readTree(sb.toString());
         JsonNode itemsNode = node.path("response").path("body").path("items").path("item");

         List<WeatherDto> weatherItems = objectMapper2.convertValue(itemsNode, new TypeReference<List<WeatherDto>>() {});

        
        
        System.out.println(node);
		
		
		UtilFunction.setSearch(vo);
		
		int roomcount = service.roomCount();
		
		if(roomcount > 0)
		{
			vo.setPagingVo(roomcount);
			//httpSession.setAttribute("sessMbrSeq",  2);
			dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
			//httpSession.invalidate();
			model.addAttribute("list", service.roomList(vo));
			model.addAttribute("mylist", service.myroomList(dto));
			model.addAttribute("weather", weatherItems);
		}
		
		
		
		return Constants.PATH_CHAT + "chatroom";
	}
	@RequestMapping(value = "/roomListPaging")
	public String roomListPaging(@ModelAttribute("vo") ChatVo vo,ChatDto dto ,Model model,HttpSession httpSession) throws Exception {
		
		UtilFunction.setSearch(vo);
		
		int roomcount = service.roomCount();
		
		if(roomcount > 0)
		{
			vo.setPagingVo(roomcount);		
			model.addAttribute("list", service.roomList(vo));
		}
	
		return Constants.PATH_CHAT + "chatroomAjax";
	}
	
	
	@RequestMapping(value = "/chatting")
	public String chatting(@RequestParam("romSeq") String romSeq, ChatDto dto, Model model,HttpSession httpSession) throws Exception{
		dto.setRomSeq(Integer.parseInt(romSeq));
		dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		model.addAttribute("item", service.roomOne(dto));
		model.addAttribute("mbrlist", service.roomMember(dto));
		return Constants.PATH_CHAT + "chatting";
	}
	
	@RequestMapping(value = "/chatcreate")
	public String chatcreate(Model model) throws Exception {
		model.addAttribute("list", service.roomimg());
		return Constants.PATH_CHAT + "chatcreate";
	}
	
	@RequestMapping(value="/chatupdate")
	public String chatupdate(ChatDto dto, Model model,HttpSession httpSession) throws Exception
	{
		dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		model.addAttribute("item", service.roomOne(dto));
		return Constants.PATH_CHAT + "chatupdate";
	}
	
	
	
	
	
//	채팅방 수정
	@RequestMapping(value ="/chatupdates")
	public String chatupdates(ChatDto dto, RedirectAttributes redirectAttributes) throws Exception
	{
		service.chatupdates(dto);
		 redirectAttributes.addAttribute("romSeq", dto.getRomSeq()); 
		return "redirect:/chatting";
	}
	
//	채팅방 만들기
	@RequestMapping(value = "/chatroominst")
	public String chatroominst(ChatDto dto,HttpSession httpSession) throws Exception
	{
		dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		service.chatroominst(dto);
		service.chatroommanager(dto);
	
		return "redirect:/chatroom";
	}
	
//	채팅방 참여
	@RequestMapping(value = "/roomcheckinst")
	public String roomcheckinst(ChatDto dto,HttpSession httpSession) throws Exception
	{
		dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		service.roomcheckinst(dto);
		
		return "redirect:/chatting" + "?romSeq=" + dto.getRomSeq();
	}
	
//	채팅방나가기
	@RequestMapping(value= "/memberdelete")
	public String memberdelete(ChatDto dto , HttpSession httpSession) throws Exception
	{
		dto.setMbrSeq((String)httpSession.getAttribute("sessMbrSeq"));
		service.memberdelete(dto);
		
		return "redirect:/chatroom";
	}
	
//	채팅방 삭제 
	@RequestMapping(value ="/roomdelete")
	public String roomdelete(ChatDto dto) throws Exception
	{
		service.roomdelete(dto);
		return "redirect:/chatroom";
	}
	
	
	
	
//	채팅방 참여여부 검사
	@ResponseBody
	@RequestMapping(value = "/checkroom")
	public Map<String, Object> checkroom(Model model,HttpSession httpSession,ChatDto dto) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		System.out.println(dto.getMbrSeq()+"-----------------------------");
		if(dto.getMbrSeq() != null)
		{
			ChatDto rDto = service.roomcheckinOne(dto);
			ChatDto rDto2 = service.roomOne(dto);
			if(rDto != null && rDto2.getRomPersonnel() >= rDto2.getCurrentStaff())
			{
				returnMap.put("rt", "success");
			}
			else if(rDto2.getRomPersonnel() <= rDto2.getCurrentStaff())
			{
				returnMap.put("rt", "full");
			}
			else
			{
				returnMap.put("rt", "success2");
				
			}
		}
		else 
		{
			returnMap.put("rt", "false");
			
		}
	
		return returnMap;
	}


}
