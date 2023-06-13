package com.event.vue.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.crypto.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.event.vue.domain.BoardVO;
import com.event.vue.domain.ReplyVO;
import com.event.vue.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin
@Controller
@EnableAutoConfiguration
public class BoardController {

  private static final String HOST_URL = "192.115.312.41";
  
@Autowired
  private BoardService boardService;
  
  @GetMapping(value = "/conTest")
  private static HttpURLConnection testHttpUrlConnection() throws IOException {
      long startTime = System.currentTimeMillis();
      URL url = new URL("http://dummy.restapiexample.com/api/v1/employees");
      HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
      long endTime = System.currentTimeMillis();
      
      int statusCode = httpConn.getResponseCode();        
      String pf = (statusCode == 200) ? "success" : "fail";
      
      System.out.println("1. connection " + pf + " in " + (endTime - startTime) + " millisecond");
      System.out.println("2. Response code: " + statusCode);
      
      return httpConn;
  }
  
	/*
	 * @GetMapping Data actionAPI(JSONArray commandlist) { HttpURLConnection conn =
	 * null; JSONObject responseJson = null;
	 * 
	 * try { // URL 설정 URL url = new URL(this.HOST_URL + "/action");
	 * 
	 * conn = (HttpURLConnection) url.openConnection(); // Request 형식 설정
	 * conn.setRequestMethod("POST"); conn.setRequestProperty("X-Auth-Token",
	 * this.token); conn.setRequestProperty("Content-Type", "application/json");
	 * 
	 * // request에 JSON data 준비 conn.setDoOutput(true); BufferedWriter bw = new
	 * BufferedWriter(new OutputStreamWriter(conn.getOutputStream())); // commands라는
	 * JSONArray를 담을 JSONObject 생성 JSONObject commands = new JSONObject();
	 * commands.put("commands", commandlist); // request에 쓰기
	 * bw.write(commands.toString()); bw.flush(); bw.close();
	 * 
	 * // 보내고 결과값 받기 int responseCode = conn.getResponseCode(); if (responseCode ==
	 * 400) { System.out.println(
	 * "400:: 해당 명령을 실행할 수 없음 (실행할 수 없는 상태일 때, 엘리베이터 수와 Command 수가 일치하지 않을 때, 엘리베이터 정원을 초과하여 태울 때)"
	 * ); } else if (responseCode == 401) {
	 * System.out.println("401:: X-Auth-Token Header가 잘못됨"); } else if (responseCode
	 * == 500) { System.out.println("500:: 서버 에러, 문의 필요"); } else { // 성공 후 응답 JSON
	 * 데이터받기 BufferedReader br = new BufferedReader(new
	 * InputStreamReader(conn.getInputStream())); StringBuilder sb = new
	 * StringBuilder(); String line = ""; while ((line = br.readLine()) != null) {
	 * sb.append(line); }
	 * 
	 * responseJson = new JSONObject(sb.toString());
	 * 
	 * } } catch (JSONException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); } catch (Exception e) {
	 * System.out.println("not JSON Format response"); e.printStackTrace(); } return
	 * parser.JSONtoData(responseJson); }
	 */
  
  /**
   * 게시글 목록 조회
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @GetMapping(value="/board/list")
  public List<BoardVO> selectBoardList() throws Exception {
	  System.out.println("boardList");
//    String schType = request.getParameter("schType");
//    String schVal = request.getParameter("schVal");
//    int rows = Integer.parseInt(request.getParameter("rows"));
//    int page = Integer.parseInt(request.getParameter("page"));
//    String[] sort = URLDecoder.decode(request.getParameter("sort"), "UTF-8").split(",");

    BoardVO vo = new BoardVO();
//    vo.setSchType(schType);
//    vo.setSchVal(schVal);
//    vo.setStartNo(((page * rows) - rows) + 1);
//    vo.setEndNo(page * rows);
//    vo.setSort(sort);

    List<BoardVO> result = boardService.selectBoardList();
    int total = 0;
//    if(result.size() > 0) {
//      total = boardService.selectBoardListCount(vo);
//    }
    
    
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("data", result);
    map.put("total", total);

    return result;
  }
  
  @GetMapping("/board/{id}")
  public BoardVO getBoard(@PathVariable Long id) throws Exception {
		System.out.println("boardDetail컨트롤러 진입");

	  String ids = String.valueOf(id);
	  
	  BoardVO vo  = boardService.getBoard(ids);
	  System.out.println(vo.getContents());
	  System.out.println(vo.getTitle());
	  System.err.println(vo.getCreatedAt());
	  System.out.println(vo.getAuthor());
      return vo;
  }
  
  @PostMapping("/board")
  public void  create(@RequestBody BoardVO boardVO) {
		System.out.println("create");
		boardService.create(boardVO);
	  }
  
  
	@ResponseBody
	@GetMapping(value = "/board/detail")
	public List<BoardVO> selectBoardDetail() throws Exception {
		System.out.println("boardDetail");

		BoardVO vo = new BoardVO();
//    vo.setSchType(schType);
//    vo.setSchVal(schVal);
//    vo.setStartNo(((page * rows) - rows) + 1);
//    vo.setEndNo(page * rows);
//    vo.setSort(sort);

		List<BoardVO> result = boardService.selectBoardList();
		int total = 0;
//    if(result.size() > 0) {
//      total = boardService.selectBoardListCount(vo);
//    }

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", result);
		map.put("total", total);
		for (Entry<String, Object> entrySet : map.entrySet()) {
			System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
		}

		return result;
	}
  /**
   * 게시글 상세 조회
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @GetMapping(value = "/detail")
  public BoardVO selectBoardDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String docNo = request.getParameter("docNo");

    BoardVO vo = new BoardVO();
    vo.setDocNo(Integer.parseInt(docNo));

    // 조회수 증가
    boardService.increaseViewCount(vo);

    BoardVO result = boardService.selectBoardDetail(vo);

    return result;
  }

  /**
   * 게시글 작성
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/insert")
  public int insertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    BoardVO vo = new BoardVO();
    vo.setTitle(title);
    vo.setContent(content);
    // TODO 로그인 사용자로 작성자 받기
    vo.setWriter("Test Writer");

    int result = 0; // 신규 게시글 번호
    
    int rows = boardService.insertBoard(vo);

    if(rows > 0) {
      result = vo.getDocNo();
    }

    return result;
  }

  /**
   * 게시글 수정
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/update")
  public int updateBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    BoardVO vo = new BoardVO();
    vo.setDocNo(Integer.parseInt(docNo));
    vo.setTitle(title);
    vo.setContent(content);

    int rows = boardService.updateBoard(vo);

    return rows;
  }

  /**
   * 게시글 삭제
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/delete")
  public int deleteBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");

    BoardVO vo = new BoardVO();
    vo.setDocNo(Integer.parseInt(docNo));

    // 댓글 유무 확인 후 같이 삭제
    ReplyVO replyVO = new ReplyVO();
    replyVO.setDocNo(Integer.parseInt(docNo));

    List<ReplyVO> result = boardService.selectReplyList(replyVO);

    if(result.size() > 0) {
      boardService.deleteReply(replyVO);
    }

    int rows = boardService.deleteBoard(vo);

    return rows;
  }

  /**
   * 댓글 조회
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @GetMapping(value = "/reply/list")
  public List<ReplyVO> selectReplyList(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");

    ReplyVO vo = new ReplyVO();
    vo.setDocNo(Integer.parseInt(docNo));

    List<ReplyVO> result = boardService.selectReplyList(vo);

    return result;
  }

  /**
   * 댓글 작성
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/reply/insert")
  public int insertReply(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");
    String comment = request.getParameter("comment");

    ReplyVO vo = new ReplyVO();
    vo.setDocNo(Integer.parseInt(docNo));
    vo.setWriter("Test Writer");
    vo.setContent(comment);

    int rows = boardService.insertReply(vo);

    return rows;
  }

  /**
   * 댓글 수정
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/reply/update")
  public int updateReply(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String replyNo = request.getParameter("replyNo");
    String docNo = request.getParameter("docNo");
    String comment = request.getParameter("comment");

    ReplyVO vo = new ReplyVO();
    vo.setReplyNo(Integer.parseInt(replyNo));
    vo.setDocNo(Integer.parseInt(docNo));
    vo.setContent(comment);

    int rows = boardService.updateReply(vo);

    return rows;
  }

  /**
   * 댓글 삭제
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/reply/delete")
  public int deleteReply(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String replyNo = request.getParameter("replyNo");
    String docNo = request.getParameter("docNo");

    ReplyVO vo = new ReplyVO();
    vo.setReplyNo(Integer.parseInt(replyNo));
    vo.setDocNo(Integer.parseInt(docNo));

    int rows = boardService.deleteReply(vo);

    return rows;
  }
  
}