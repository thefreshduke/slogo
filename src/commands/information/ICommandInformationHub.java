package commands.information;

import java.util.Collection;
import java.util.Set;

import backendExceptions.BackendException;

public interface ICommandInformationHub {

    public IInformationContainer getContainer (Class<? extends IInformationContainer> containerType);

    public Collection<IInformationContainer> getContainers (
            Set<Class<? extends IInformationContainer>> containerTypes) throws BackendException;
}
