/*
 * @author: Phạm Trung Hiếu
 * You can change this file's content at will
 * but please give credit to the original author.
 */

package duelgame.mount;

import duelgame.weapon.Weapons;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum Mounts {
	NO_MOUNT(NoMount.class);
	
	private final Class<? extends Mount> mountClass;
	
	Mounts(Class<? extends Mount> mountClass) {
		this.mountClass = mountClass;
	}

	public Class<? extends Mount> getMountClass() {
		return mountClass;
	}
	
	public String getName() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return mountClass.getMethod("name").invoke(null).toString();
	}
	
	public Mount create() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> paramTypes[] = {};
		return mountClass.getDeclaredConstructor(paramTypes).newInstance();
	}
	
	@Override
	public String toString() {
		try {
			return getName();
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			Logger.getLogger(Weapons.class.getName()).log(Level.SEVERE, null, ex);
		}
		return "";
	}
}
