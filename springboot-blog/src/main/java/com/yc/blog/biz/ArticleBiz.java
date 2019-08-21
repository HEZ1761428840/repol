package com.yc.blog.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yc.blog.bean.Article;
import com.yc.blog.bean.ArticleExample;
import com.yc.blog.dao.ArticleMapper;

@Service
public class ArticleBiz {
	
	@Autowired
	private ArticleMapper am;

	/**
	 * 查询最新发布的文章
	 * @param page  页数
	 * @return
	 */
	public List<Article> queryNewArticle(int page) {
		ArticleExample example = new ArticleExample();
		example.setOrderByClause("createTime desc");
		PageHelper.startPage(page, 5);
		
		return am.selectByExampleWithBLOBs(example);
	}

	/**
	 * 通过分类的id 查询出 各类型的所有内容
	 * @param id  类别的id
	 * @param page 页数
	 * @return
	 */
	public List<Article> queryByCategoryId(int id, int page) {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryidEqualTo(id);
		example.setOrderByClause("createTime desc");
		PageHelper.startPage(page, 5);
		
		return am.selectByExampleWithBLOBs(example);
	}

	@Transactional //加入事物注解
	public Article read(int id) {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andIdEqualTo(id);
		Article a = am.selectByPrimaryKey(id);
		//更新阅读次数
		a.setReadcnt((a.getReadcnt() == null ? 0 : a.getReadcnt()) + 1);
		am.updateByPrimaryKey(a);
		return a;
	}

	public List<Article> queryRela(Integer categoryid) {
		ArticleExample example = new ArticleExample();
		//按时间降序
		example.setOrderByClause("createTime desc");
		//查看相关类型文章
		example.createCriteria().andCategoryidEqualTo(categoryid);
		//查十条记录
		PageHelper.startPage(1, 10);
		
		return am.selectByExample(example);
	}
	
	/**
	 * 添加博文
	 * @param article
	 */
	public void save(Article article) {
		am.insert(article);
	}
	

}
