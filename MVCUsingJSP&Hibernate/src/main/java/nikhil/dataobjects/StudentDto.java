package nikhil.dataobjects;

import java.io.Serializable;

public class StudentDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer sid;
	private String sname;
	private Integer sage;
	private String saddress;

	public StudentDto() {
		System.out.println("StudentDTO Object is created...");
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Integer getSage() {
		return sage;
	}

	public void setSage(Integer sage) {
		this.sage = sage;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	@Override
	public String toString() {
		return "StudentDto [sid=" + sid + ", sname=" + sname + ", sage=" + sage + ", saddress=" + saddress + "]";
	}

}
