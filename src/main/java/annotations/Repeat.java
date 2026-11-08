package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// аннотация будет доступна во время выполнения программы
@Retention(RetentionPolicy.RUNTIME)
// аннотацию можно ставить только над методами
@Target(ElementType.METHOD)
public @interface Repeat {
    int times();
}
