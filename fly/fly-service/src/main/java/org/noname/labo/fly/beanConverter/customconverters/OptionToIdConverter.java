package org.noname.labo.fly.beanConverter.customconverters;

import org.dozer.DozerConverter;
import org.noname.labo.fly.dao.UserOptionsDao;
import org.noname.labo.fly.domain.UserOptions;
import org.springframework.beans.factory.annotation.Autowired;

public class OptionToIdConverter extends DozerConverter<UserOptions, Integer> {

	@Autowired
	private UserOptionsDao optionsDao;
	
	public OptionToIdConverter() {
		super(UserOptions.class, Integer.class);
	}

	@Override
	public Integer convertTo(UserOptions source, Integer destination) {
		if (source == null) {
			return null;
		}

		return source.getOptionId();
	}

	@Override
	public UserOptions convertFrom(Integer source, UserOptions destination) {
		if (source == null) {
			return null;
		}

		return optionsDao.findById(source).get();
	}
}
