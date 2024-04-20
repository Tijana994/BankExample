package bank.models;

import java.util.List;

import com.security.model.validation.annotations.PurposeAnnotation;

@PurposeAnnotation(details = "Text", subPurposes = "SubPurposes", reason = "Reason", reasonSubtype = "SubType")
public class Purpose {
	public Purpose(String text, List<Purpose> subPurposes, int reason, int subType) {
		super();
		Text = text;
		SubPurposes = subPurposes;
		Reason = reason;
		SubType = subType;
	}
	public String Text;
	public List<Purpose> SubPurposes;
	public int Reason;
	public int SubType;
}
