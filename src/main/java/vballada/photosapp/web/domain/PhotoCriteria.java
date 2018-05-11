package vballada.photosapp.web.domain;

/**
 * @author BALLADA_V
 *         <p>
 *         Search Criteria
 *         </p>
 */
public class Criteria {

	private String location;
	private String startdate;
	private String enddate;
	private int pageNumber;
	private int size;
	private String sort;
	private String dir;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

}
