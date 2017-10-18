package plugin.skill.magic;

import org.arios.game.component.Component;
import org.arios.game.content.skill.free.magic.MagicSpell;
import org.arios.game.node.Node;
import org.arios.game.node.entity.Entity;
import org.arios.game.node.entity.combat.equipment.SpellType;
import org.arios.game.node.entity.player.Player;
import org.arios.game.node.entity.player.link.SpellBookManager.SpellBook;
import org.arios.plugin.Plugin;

/**
 * Represents the enchant crossbow spell.
 * @author 'Vexia.
 * @version 1.0
 */
public final class EnchantCrossbowSpell extends MagicSpell {

    /**
     * Constructs a new {@code EnchantCrossbowSpell} {@code Object}.
     */
    public EnchantCrossbowSpell() {
	super(SpellBook.MODERN, 4, 0, null, null, null, null);
    }

    @Override
    public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
	SpellBook.MODERN.register(3, this);
	return null;
    }

    @Override
    public boolean cast(Entity entity, Node target) {
	final Player player = ((Player) entity);
	player.getInterfaceManager().open(new Component(432));
	int[][] data = new int[][] { { 17, 879 }, { 21, 9335 }, { 25, 880 }, { 28, 9336 }, { 31, 9337 }, { 34, 9338 }, { 37, 9339 }, { 40, 9340 }, { 43, 9341 }, { 46, 9342 } };
	for (int i = 0; i < data.length; i++) {
	    player.getPacketDispatch().sendItemZoomOnInterface(data[i][1], 10, 270, 432, data[i][0]);
	}
	return true;
    }

}
