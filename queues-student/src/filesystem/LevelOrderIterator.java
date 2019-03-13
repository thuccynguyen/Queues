package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import structures.Queue;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File> {
	Queue<File> fileQueue = new Queue<File>();
	Queue<File> finalQueue = new Queue<File>();
	
	
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
        	// TODO 1
		if (!rootNode.exists() || rootNode == null) {
			throw new FileNotFoundException();
		}
		
		fileQueue.enqueue(rootNode);
		
		while(!fileQueue.isEmpty())	{
			if (fileQueue.peek().isDirectory()) {
				File[] entries = fileQueue.peek().listFiles();
				Arrays.sort(entries);
				for (File f: entries) {
					fileQueue.enqueue(f);
				}
			}
			
			finalQueue.enqueue(fileQueue.dequeue());
		}
	}
	
	@Override
	public boolean hasNext() {
        	// TODO 2
		boolean hasNext = false;
		
		if (!finalQueue.isEmpty()) {
			hasNext = true;
		}
		return hasNext;
	}

	@Override
	public File next() throws NoSuchElementException {
        	// TODO 3
		if (finalQueue.isEmpty()) {
			throw new NoSuchElementException();
		}
        return finalQueue.dequeue();
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
