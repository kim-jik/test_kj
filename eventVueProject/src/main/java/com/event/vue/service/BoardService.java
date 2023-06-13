package com.event.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.event.vue.domain.BoardVO;
import com.event.vue.domain.ReplyVO;
import com.event.vue.repository.BoardMapper;

@Service
@Component
@Repository
public class BoardService {
	
	@Autowired
  BoardMapper boardMapper;
	
	public List<BoardVO> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	public int selectBoardListCount(BoardVO vo) throws Exception {
		return boardMapper.selectBoardListCount(vo);
	}

	public BoardVO selectBoardDetail(BoardVO vo) throws Exception{
		return boardMapper.selectBoardDetail(vo);
	}
	
	public int insertBoard(BoardVO vo) throws Exception {
		return boardMapper.insertBoard(vo);
	}

	public int updateBoard(BoardVO vo) throws Exception{
		return boardMapper.updateBoard(vo);
	}

	public int deleteBoard(BoardVO vo) throws Exception {
		return boardMapper.deleteBoard(vo);
	}

	public void increaseViewCount(BoardVO vo) throws Exception {
		boardMapper.increaseViewCount(vo);
	}

	public List<ReplyVO> selectReplyList(ReplyVO vo) throws Exception {
		return boardMapper.selectReplyList(vo);
	}

	public int insertReply(ReplyVO vo) throws Exception {
		return boardMapper.insertReply(vo);
	}

	public int updateReply(ReplyVO vo) throws Exception {
		return boardMapper.updateReply(vo);
	}

	public int deleteReply(ReplyVO vo) throws Exception {
		return boardMapper.deleteReply(vo);
	}

	public BoardVO getBoard(String id) throws Exception {
		return boardMapper.selectDetail(id);
	}

	public void  create(BoardVO boardVO) {
		// TODO Auto-generated method stub
		 boardMapper.create(boardVO);
	}
  
}