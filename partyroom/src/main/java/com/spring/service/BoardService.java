package com.spring.service;

import java.util.List;

import com.spring.domain.AttachVO;
import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

public interface BoardService {
	
	public void register(BoardVO board);
	
	public BoardVO get(Long bno);
	
	public boolean modify(BoardVO board);
	
	public boolean remove(Long bno);
	
	public List<BoardVO> getList(Criteria cri);

	public int getTotal(Criteria cri);

	public BoardVO deleteAll(String writer);

	public List<AttachVO> getAttachList(Long bno);

	public void updateViewcnt(Long bno);



}
