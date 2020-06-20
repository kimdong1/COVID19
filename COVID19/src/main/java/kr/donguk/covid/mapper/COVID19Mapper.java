package kr.donguk.covid.mapper;

import java.util.List;

import kr.donguk.covid.vo.COVID19;

public interface COVID19Mapper {
	public void covid19Insert(COVID19 covid19);
	public void covid19Update(COVID19 covid19);
	public List<COVID19> covid19SelectList();
}
