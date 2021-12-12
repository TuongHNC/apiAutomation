package mock;

public interface MockService<T> {
    void up(final String hostName, final String basePath, final String dataSource);

    T getInstance();

    void down();
}
