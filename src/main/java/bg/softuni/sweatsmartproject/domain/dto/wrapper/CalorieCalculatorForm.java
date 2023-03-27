package bg.softuni.sweatsmartproject.domain.dto.wrapper;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.Name;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalorieCalculatorForm {

    @NotNull
    public String sex;

    @Size(min = 10, max = 300)
    public double weight;

    @Size(min = 20, max = 250)
    public double height;

    @Size(min = 7, max = 100)
    public int age;

    @NotNull
    public String activityLevel;
}