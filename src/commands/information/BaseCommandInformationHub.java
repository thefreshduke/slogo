package commands.information;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public abstract class BaseCommandInformationHub {

	public BaseCommandInformationHub(){}

	public abstract IInformationContainer getContainer (Class<? extends IInformationContainer> containerType);

	public Collection<IInformationContainer> getContainers(Set<Class<? extends IInformationContainer>> containerTypes){
		ArrayList<IInformationContainer> containerList = new ArrayList<>();
		for(Class<? extends IInformationContainer> containerType : containerTypes){
			IInformationContainer informationContainer = getContainer(containerType);
			if(informationContainer == null) {
				return null;
			}
		}
		return containerList;
	}
}
