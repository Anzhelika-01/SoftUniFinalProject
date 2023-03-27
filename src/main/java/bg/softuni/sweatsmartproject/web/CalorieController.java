package bg.softuni.sweatsmartproject.web;

import bg.softuni.sweatsmartproject.domain.dto.wrapper.CalorieCalculatorForm;
import bg.softuni.sweatsmartproject.service.CalorieCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CalorieController extends BaseController{

    private final CalorieCalculator calorieCalculator;

    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";

    @Autowired
    public CalorieController(CalorieCalculator calorieCalculator) {
        this.calorieCalculator = calorieCalculator;
    }

    @GetMapping("/calorie-calculator")
    public ModelAndView getCalculator() {
        return super.view("calorie-calculator");
    }

    @PostMapping("/calorie-calculator")
    public void calculateCalories(@ModelAttribute("calorieCalculatorForm") CalorieCalculatorForm calorieCalculatorForm,
                                    BindingResult result, RedirectAttributes redirectAttributes,
                                    Model model) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("calorieCalculatorForm", calorieCalculatorForm)
                    .addFlashAttribute(BINDING_RESULT_PATH + "calorieCalculatorForm", result);

//            return super.redirect("calorie-calculator");
        }

        double caloriesPerDay = calorieCalculator.calculateCalories(calorieCalculatorForm.getSex(),
                calorieCalculatorForm.getWeight(), calorieCalculatorForm.getHeight(),
                calorieCalculatorForm.getAge(), calorieCalculatorForm.getActivityLevel());

        model.addAttribute("caloriesPerDay", caloriesPerDay);

//        return super.view("calorie-calculator");
    }

    @ModelAttribute(name = "calorieCalculatorForm")
    public CalorieCalculatorForm getCalorieCalculatorForm() {
        return new CalorieCalculatorForm();
    }
}