package database;

public interface IDatabase<T> {

    T get();

    T connect();

    T reconnect(int tryInSeconds);

    void close();

}
