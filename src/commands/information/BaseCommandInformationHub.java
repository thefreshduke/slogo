package commands.information;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import backendExceptions.BackendException;

public abstract class BaseCommandInformationHub {

	public BaseCommandInformationHub(){}

	public abstract IInformationContainer getContainer (Class<? extends IInformationContainer> containerType) throws BackendException;

	public Collection<IInformationContainer> getContainers(Set<Class<? extends IInformationContainer>> containerTypes) throws BackendException {
		ArrayList<IInformationContainer> containerList = new ArrayList<>();
		for(Class<? extends IInformationContainer> containerType : containerTypes){
			IInformationContainer informationContainer = getContainer(containerType);
			if(informationContainer == null) {
				return null;
			} else {
				containerList.add(informationContainer);
			}
		}
		return containerList;
	}
}
