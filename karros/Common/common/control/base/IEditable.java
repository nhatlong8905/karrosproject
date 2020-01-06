package common.control.base;

public interface IEditable {
	void enter(CharSequence... value);
	
	void setText(String value);

	void setValue(String value);
}
