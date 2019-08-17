package com.yc.blog.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.blog.bean.Category;
import com.yc.blog.dao.CategoryMapper;

@Service
public class CategoryBiz {
	
	@Resource
	private CategoryMapper cm;
	
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<Category> selectCategoryAll(){
		return cm.selectByExample(null);
	}
}
