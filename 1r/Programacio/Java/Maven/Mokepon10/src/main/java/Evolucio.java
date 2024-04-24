
public class Evolucio {

	String pre;
	String post;

	public Evolucio() {
		super();
	}
	
	public Evolucio(String pre, String post) {
		super();
		this.pre = pre;
		this.post = post;
	}

	public String getPre() {
		return pre;
	}

	public void setPre(String pre) {
		this.pre = pre;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Evolucio [pre=" + pre + ", post=" + post + "]";
	}
		
}
