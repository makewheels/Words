/**
  * Copyright 2018 bejson.com 
  */
package queryword.jinshan.bean;

import java.util.List;

/**
 * Auto-generated: 2018-03-23 10:47:51
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Parts {

	private String part;
	private List<String> means;

	public void setPart(String part) {
		this.part = part;
	}

	public String getPart() {
		return part;
	}

	public void setMeans(List<String> means) {
		this.means = means;
	}

	public List<String> getMeans() {
		return means;
	}

	@Override
	public String toString() {
		return "Parts [part=" + part + ", means=" + means + "]";
	}

}