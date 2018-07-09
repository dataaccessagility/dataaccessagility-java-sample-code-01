package dataaccessobjects;

public class ScalarValue<T>
{
	private T _value;

	public ScalarValue()
	{
	}

	public void setValue(T val)
	{
		this._value = val;
	}

	public T getValue()
	{
		return this._value;
	}

}
