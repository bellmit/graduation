package com.software.datasource;


import com.software.exception.BaseException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum DataSourceEnum {

	JIZHONGKU("Jizhongku","Jizhongku","集中库"),
	CPWSDYFXXT("Cpwsdyfxxt","Cpwsdyfxxt","裁判文书调研分析系统"),

	TJGY("120000 200","Tjgy","天津市高级人民法院"),
	TJYZY("120100 210","Tjyzy","天津市第一中级人民法院"),
	TJEZY("120200 220","Tjezy","天津市第二中级人民法院"),
	TJSZY("120300 230", "Tjszy", "天津市第三中级人民法院"),
	TJHSFY("960200 230","Tjhsfy","天津海事法院"),
	TJBHXQFY("120242 22A","Tjbhxqfy","天津市滨海新区人民法院"),
	TJHPFY("120101 211","Tjhpfy","天津市和平区人民法院"),
	TJNKFY("120104 212","Tjnkfy","天津市南开区人民法院"),
	TJHBFY("120105 213","Tjhbfy","天津市河北区人民法院"),
	TJHQFY("120106 214","Tjhqfy","天津市红桥区人民法院"),
	TJXQFY("120107 215","Tjxqfy","天津市西青区人民法院"),
	TJBCFY("120108 216","Tjbcfy","天津市北辰区人民法院"),
	TJHDFY("120202 221","Tjhdfy","天津市河东区人民法院"),
	TJHXFY("120203 222","Tjhxfy","天津市河西区人民法院"),
	TJTGFY("120204 223","Tjbhxqfy","天津市滨海新区人民法院塘沽审判区"),
	TJHGFY("120205 224","Tjbhxqfy","天津市滨海新区人民法院汉沽审判区"),
	TJDGFY("120206 225","Tjbhxqfy","天津市滨海新区人民法院大港审判区"),
	TJDLFY("120207 226","Tjdlfy","天津市东丽区人民法院"),
	TJJNFY("120208 227","Tjjnfy","天津市津南区人民法院"),
	TJNHFY("120221 228","Tjnhfy","天津市宁河县人民法院"),
	TJWQFY("120222 217","Tjwqfy","天津市武清区人民法院"),
	TJJHFY("120223 218","Tjjhfy","天津市静海县人民法院"),
	TJBDFY("120224 219","Tjbdfy","天津市宝坻区人民法院"),
	TJJXFY("120225 21A","Tjjxfy","天津市蓟县人民法院"),
	TJKFQFY("120241 229","Tjbhxqfy","天津市滨海新区人民法院功能区审判区"),
	TJTLFY("920103 132","Tjtlfy","天津铁路运输法院"),
	TJZMFY("120304 234","Tjzmfy","天津自由贸易试验区人民法院");

	String fydm;
	String source;
	String fymc;

	/**
	 * @param fydm
	 * @param source
	 */
	private DataSourceEnum(String fydm, String source,String fymc) {
		this.fydm = fydm;
		this.source = source;
		this.fymc = fymc;
	}
	
	public static String getFydmBySource(String source){
		for (DataSourceEnum src : DataSourceEnum.values()) {
			if (StringUtils.equals(source, src.getSource())) {
				return src.getFydm();
			}
		}
		return "";
	}
	public static String getFymcByFydm(String fydm){
		for (DataSourceEnum src : DataSourceEnum.values()) {
			if (StringUtils.equals(fydm, src.getFydm())) {
				return src.getFymc();
			}
		}
		return "";
	}
	//通过法院地区获得法院代码
	public static String getFydqByFydm(String fydm){
		Map<String,String> map=new HashMap();
		map.put("120242 22A","滨海新区");
		map.put("120101 211","和平区");
		map.put("120104 212","南开区");
		map.put("120105 213","河北区");
		map.put("120106 214","红桥区");
		map.put("120107 215","西青区");
		map.put("120108 216","北辰区");
		map.put("120202 221","河东区");
		map.put("120203 222","河西区");
		map.put("120207 226","东丽区");
		map.put("120208 227","津南区");
		map.put("120221 228","宁河县");
		map.put("120222 217","武清区");
		map.put("120223 218","静海县");
		map.put("120224 219","宝坻区");
		map.put("120225 21A","蓟县");

		return map.getOrDefault(fydm,"");
	}
	public static List<String> getSubFydm(String fydm) {
		List<String> fydmList = new ArrayList<String>();
		if (StringUtils.equals(TJGY.getFydm(), fydm)) {
			/*fydmList.add(DataSourceEnum.TJGY.getFydm());
			fydmList.add(DataSourceEnum.TJYZY.getFydm());
			fydmList.add(DataSourceEnum.TJEZY.getFydm());*/
			/*fydmList.add(TJGY.getFydm());
			fydmList.add(TJYZY.getFydm());
			fydmList.add(TJEZY.getFydm());
			fydmList.add(TJSZY.getFydm());*/
			fydmList.add(TJHPFY.getFydm());
			fydmList.add(TJNKFY.getFydm());
			fydmList.add(TJHBFY.getFydm());
			fydmList.add(TJHQFY.getFydm());
			fydmList.add(TJXQFY.getFydm());
			fydmList.add(TJBCFY.getFydm());
			fydmList.add(TJWQFY.getFydm());
			fydmList.add(TJJHFY.getFydm());
			fydmList.add(TJBDFY.getFydm());
			fydmList.add(TJJXFY.getFydm());
			fydmList.add(TJHDFY.getFydm());
			fydmList.add(TJHXFY.getFydm());
			//fydmList.add(TJTGFY.getFydm());
			//fydmList.add(TJHGFY.getFydm());
			//fydmList.add(TJDGFY.getFydm());
			fydmList.add(TJDLFY.getFydm());
			fydmList.add(TJJNFY.getFydm());
			fydmList.add(TJNHFY.getFydm());
			//fydmList.add(TJKFQFY.getFydm());
			fydmList.add(TJBHXQFY.getFydm());
		} else if (StringUtils.equals(TJYZY.getFydm(), fydm)) {
			fydmList.add(TJHPFY.getFydm());
			fydmList.add(TJNKFY.getFydm());
			fydmList.add(TJHBFY.getFydm());
			fydmList.add(TJHQFY.getFydm());
			fydmList.add(TJXQFY.getFydm());
			fydmList.add(TJBCFY.getFydm());
			fydmList.add(TJWQFY.getFydm());
			fydmList.add(TJJHFY.getFydm());
			fydmList.add(TJBDFY.getFydm());
			fydmList.add(TJJXFY.getFydm());
		} else if (StringUtils.equals(TJEZY.getFydm(), fydm)) {
			fydmList.add(TJHDFY.getFydm());
			fydmList.add(TJHXFY.getFydm());
			fydmList.add(TJTGFY.getFydm());
			fydmList.add(TJHGFY.getFydm());
			fydmList.add(TJDGFY.getFydm());
			fydmList.add(TJDLFY.getFydm());
			fydmList.add(TJJNFY.getFydm());
			fydmList.add(TJNHFY.getFydm());
			fydmList.add(TJKFQFY.getFydm());
			//fydmList.add(TJBHXQFY.getFydm());
		} /*else if (StringUtils.equals(TJHSFY.getFydm(), fydm)) {
			fydmList.add(TJTGFY.getFydm());
			fydmList.add(TJHGFY.getFydm());
			fydmList.add(TJDGFY.getFydm());
			fydmList.add(TJBHXQFY.getFydm());
			fydmList.add(TJKFQFY.getFydm());
		}*/ else {
			fydmList.add(fydm);
		}

		return fydmList;
	}

	/**
	 * 返回上级法院
	 * @param fydm
	 * @return
	 */
	public static DataSourceEnum getSuperFy(String fydm) throws BaseException{
		List<String> fydmList = getSubFydm(TJGY.getFydm());
		if(fydmList.contains(fydm))
			return TJGY;

		fydmList = getSubFydm(TJYZY.getFydm());
		if(fydmList.contains(fydm))
			return TJYZY;

		fydmList = getSubFydm(TJEZY.getFydm());
		if(fydmList.contains(fydm))
			return TJEZY;

		throw new BaseException("未查找到可用的法院代码【"+fydm+"】");
	}

	/**
	 * @return the fydm
	 */
	public String getFydm() {
		return fydm;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	public String getFymc(){
		return fymc;
	}
}
