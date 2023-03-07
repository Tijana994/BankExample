package bank.models;

import java.util.List;

import com.security.model.validation.annotations.PurposeAnnotation;

@PurposeAnnotation(details = "Text", subPurposes = "SubPurposes")
public class Purpose {
	public String Text;
	public List<Purpose> SubPurposes;
}
