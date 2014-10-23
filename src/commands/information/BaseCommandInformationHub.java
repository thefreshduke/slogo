package commands.information;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import backendExceptions.BackendException;

public abstract class BaseCommandInformationHub {
	private Collection<IInformationContainer> myContainerList;
	public BaseCommandInformationHub(){}

	public abstract IInformationContainer getContainer (Class<? extends IInformationContainer> containerType) throws BackendException;

	public Collection<IInformationContainer> getContainers(Set<Class<? extends IInformationContainer>> containerTypes) throws BackendException {
		
		return myContainerList;
	}
	
	public void setContainers(Set<Class<? extends IInformationContainer>> containerTypes) throws BackendException {
		for(Class<? extends IInformationContainer> containerType : containerTypes){
			IInformationContainer informationContainer = getContainer(containerType);
			if(informationContainer != null) {
				myContainerList.add(informationContainer);
			} else {
				// TODO throw exception saying container couldn't be found
				// or decide to catch this error downstream
			}
		}
	} 
}
