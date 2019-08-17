package com.yc.blog.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.blog.bean.Article;
import com.yc.blog.bean.Comment;
import com.yc.blog.dao.ArticleMapper;
import com.yc.blog.dao.CommentMapper;

@Service
public class CommentBiz {

	@Resource
	private CommentMapper cm;
	
	@Resource
	private ArticleMapper am;
	
	public Comment comment(Comment comm){
		//新增评论
		cm.insertSelective(comm);
		//查询该文章的评论数
		Article a = am.selectByPrimaryKey(comm.getArticleid());
		//更新评论次数
		a.setCommcnt((a.getCommcnt() == null ? 0 : a.getCommcnt()) + 1);
		am.updateByPrimaryKey(a);
		
		return comm;
	}
}
