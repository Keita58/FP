import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"pre", "post"})
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

	@XmlElement(name="pre-evolucio")
	public String getPre() {
		return pre;
	}

	public void setPre(String pre) {
		this.pre = pre;
	}

	@XmlElement(name="post-evolucio")
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
