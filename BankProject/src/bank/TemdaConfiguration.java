package bank;

import java.util.ArrayList;

import privacyModel.DataType;
import utility.PrivacyDataFactory.DataFactory;
import utility.ProjectConfiguration.Configuration;

public class TemdaConfiguration {
	
	@SuppressWarnings("serial")
	public static void setUpConfiguration() {
		Configuration.setXmlPath("model/bankTest.xmi");
		Configuration.createDefaultConfiguration();
		Configuration.setPrivacyPolicyName("Bank example");
		Configuration.setDataSources(new ArrayList<String>()
		{{
			add("PubliclyAccessibleSources"); 
			add("Identity document");
			add("Internal");
		}});
		Configuration.setProtectionControlMethods(new ArrayList<String>()
		{{
			add("OriginalData"); 
			add("Pseudonymisation");
			add("Encryption");
		}});
		Configuration.setDefaultProtectionControls(new ArrayList<String>()
		{{
			add("OriginalData");
		}});
		
		var encription = new ArrayList<String>()
		{{
			add("Encryption");
		}};
		
		DataFactory.addPrivacyDataWithSharedPrivacyData("name", DataType.GENERAL, true, "Identity document", encription);
		DataFactory.addPrivacyDataWithSharedPrivacyData("account number", DataType.GENERAL, true, null, null);
		DataFactory.addPrivacyDataWithSharedPrivacyData("identity number", DataType.BIOMETRIC, true, "Identity document", encription);
		DataFactory.addPrivacyDataWithSharedPrivacyData("email", DataType.GENERAL, true, null, null);
		
		DataFactory.addCountry("USA", false, 21);
		DataFactory.addRegion("NYC", "USA");
	}

}
