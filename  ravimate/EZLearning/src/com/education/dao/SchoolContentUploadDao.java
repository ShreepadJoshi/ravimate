package com.education.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Example.PropertySelector;
import org.springframework.dao.DataAccessException;

import com.education.transferobj.TSchool;
import com.education.util.NotBlankPropertySelector;

public class SchoolContentUploadDao extends AbstractDAO {
	
	public int insertIntoSchool(ArrayList<TSchool> schoolList) throws DataAccessException
	{
		for(int i = 0;i<schoolList.size();i++)
		{
			TSchool school = schoolList.get(i);
			getHibernateTemplate().saveOrUpdate(school);
			if(i % 1000 == 0)
			{
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
		return schoolList.size();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TSchool> findAllSchools(TSchool school)
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TSchool.class);
		Example example = Example.create(school);
		example.excludeZeroes();
		example.setPropertySelector(new NotBlankPropertySelector());
		/*criteria.add(Expression.ne("schoolName", ""));
		criteria.add(Expression.ne("schoolBoard", ""));
		criteria.add(Expression.ne("schoolDistrict", ""));
		criteria.add(Expression.ne("schoolState", ""));*/
		criteria.add(example);
		
		//return criteria
		
		/*DetachedCriteria criteria = DetachedCriteria.forClass(TSchool.class)
									.add(Property.forName("schoolName").isNotNull())
									.add(Restrictions.ne("schoolName",""))
									.add(Property.forName("schoolName").eq(school.getSchoolName()))
									.add(Property.forName("schoolState").isNotNull())
									.add(Restrictions.ne("schoolState",""))
									.add(Property.forName("schoolDistrict").isNotNull())
									.add(Restrictions.ne("schoolDistrict",""))
									.add(Property.forName("schoolBoard").isNotNull())
									.add(Restrictions.ne("schoolBoard",""));*/
									
		return getHibernateTemplate().findByCriteria(criteria);
	}
	

}
