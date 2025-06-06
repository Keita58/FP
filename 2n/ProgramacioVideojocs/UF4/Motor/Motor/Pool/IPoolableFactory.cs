public interface IPoolableFactory<T>{

    public T Create();
    public void Activate(T element);
    public void Deactivate(T element);
    public void Delete(T element);
}