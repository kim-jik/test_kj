package com.event.vue.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.event.vue.domain.BoardVO;
import com.event.vue.domain.ReplyVO;

@Mapper
public interface BoardMapper {
  
  /**
   * 게시판 목록 조회
   * 
   * @param vo
   * @return
   * @throws Exception
   */
  public List<BoardVO> selectBoardList() throws Exception;
  	
  
  /**
   * 게시판 건수 조회
   * @param vo
   * @return
   * @throws Exception
   */
  public int selectBoardListCount(BoardVO vo) throws Exception;

  /**
   * 게시글 조회
   * @param vo
   * @return
   * @throws Exception
   */
  public BoardVO selectBoardDetail(BoardVO vo) throws Exception;

  /**
   * 게시글 등록
   * @param vo
   * @return
   * @throws Exception
   */
  public int insertBoard(BoardVO vo) throws Exception;

  /**
   * 게시글 수정
   * @param vo
   * @return
   * @throws Exception
   */
  public int updateBoard(BoardVO vo) throws Exception;

  /**
   * 게시글 삭제
   * @param vo
   * @return
   * @throws Exception
   */
  public int deleteBoard(BoardVO vo) throws Exception;

  /**
   * 조회수 증가
   * @param vo
   * @throws Exception
   */
  public void increaseViewCount(BoardVO vo) throws Exception;

  /**
   * 댓글 조회
   * @param vo
   * @return
   * @throws Exception
   */
  public List<ReplyVO> selectReplyList(ReplyVO vo) throws Exception;

  /**
   * 댓글 작성
   * @param vo
   * @return
   * @throws Exception
   */
  public int insertReply(ReplyVO vo) throws Exception;

  /**
   * 댓글 수정
   * @param vo
   * @return
   * @throws Exception
   */
  public int updateReply(ReplyVO vo) throws Exception;

  /**
   * 댓글 삭제
   * @param vo
   * @return
   * @throws Exception
   */
	public int deleteReply(ReplyVO vo) throws Exception;

	public BoardVO selectDetail(String id) throws Exception;

	public void create(BoardVO boardVO);

}