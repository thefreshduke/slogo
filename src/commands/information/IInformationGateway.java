package commands.information;

import java.util.Collection;
import java.util.Set;

import backendExceptions.BackendException;

/**
 * @author Duke The central point for all information related to the backend of
 *         the program, mostly the information required for commands. The reason
 *         for the existence of this class is to limit information access in the
 *         extending commands, in order to limit room for error for those
 *         wanting to extend commands more specific than BaseCommand, such as
 *         TurtleCommands, ControlCommands etc. At the same time, by providing a
 *         central point of information, it not only allows method command
 *         execution method signatures to be general, but also streamlines how
 *         data is accessed, which would ideally limit repeated data.
 */

public interface IInformationGateway {

	/**
	 * Gets the container corresponding to the requested container type
	 * 
	 * @param containerType
	 *            The type of container to get.
	 * @return The requested information container
	 */
	public IInformationContainer getContainer(
			Class<? extends IInformationContainer> containerType);

	/**
	 * Gets a collection of containers corresponding to the requested container
	 * types.
	 * 
	 * @param containerTypes
	 *            The types of containers to get.
	 * @return Collection of requested information containers.
	 * @throws BackendException
	 *             Any exception to be thrown.
	 */
	public Collection<IInformationContainer> getContainers(
			Set<Class<? extends IInformationContainer>> containerTypes)
			throws BackendException;

	/**
	 * Adds a container to the gateway.
	 * @param container Container to be added
	 * @throws BackendException Any exception while adding container
	 */
	public void addContainer(IInformationContainer container) throws BackendException;
}
