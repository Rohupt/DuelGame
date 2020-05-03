/*
 * @author: Phạm Trung Hiếu
 * You can change this file's content at will
 * but please give credit to the original author.
 */

package duelgame.weapon;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum Weapons {
	NO_WEAPON(NoWeapon.class);
	
	private final Class<? extends Weapon> weaponClass;
	
	Weapons(Class<? extends Weapon> weaponClass) {
		this.weaponClass = weaponClass;
	}

	public Class<? extends Weapon> getWeaponClass() {
		return weaponClass;
	}
	
	public String getName() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return weaponClass.getMethod("name").invoke(null).toString();
	}
	
	public Weapon create() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> paramTypes[] = {};
		return weaponClass.getDeclaredConstructor(paramTypes).newInstance();
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
