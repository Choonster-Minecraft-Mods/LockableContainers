package com.github.choonster.lockablecontainers.client.gui;

import com.github.choonster.lockablecontainers.LockableContainers;
import com.github.choonster.lockablecontainers.api.capability.lock.ILock;
import com.github.choonster.lockablecontainers.network.MessageLockSetLockCode;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * Allows a player to lock an {@link ILock}.
 *
 * @author Choonster
 */
public class GuiLock extends GuiScreen {

	/**
	 * The position.
	 */
	private final BlockPos pos;

	/**
	 * The facing.
	 */
	private final EnumFacing facing;

	/**
	 * The lock code text field.
	 */
	private GuiTextField lockCodeTextField;
	private GuiButton doneButton;
	private GuiButton cancelButton;

	public GuiLock(BlockPos pos, @Nullable EnumFacing facing) {
		this.pos = pos;
		this.facing = facing;
	}

	@Override
	public void updateScreen() {
		lockCodeTextField.updateCursorCounter();
	}

	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		doneButton = addButton(new GuiButton(0, width / 2 - 4 - 150, height / 4 + 120 + 12, 150, 20, I18n.format("gui.done")));
		cancelButton = addButton(new GuiButton(1, width / 2 + 4, height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel")));

		lockCodeTextField = new GuiTextField(2, fontRendererObj, width / 2 - 150, 50, 300, 20);
		lockCodeTextField.setMaxStringLength(32500);
		lockCodeTextField.setFocused(true);
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.enabled) {
			if (button.id == 0) {
				LockableContainers.NETWORK.sendToServer(new MessageLockSetLockCode(pos, facing, lockCodeTextField.getText()));
				mc.displayGuiScreen(null);
			} else if (button.id == 1) {
				mc.displayGuiScreen(null);
			}
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		lockCodeTextField.textboxKeyTyped(typedChar, keyCode);

		if (keyCode == Keyboard.KEY_ESCAPE) {
			actionPerformed(cancelButton);
		} else if (keyCode == Keyboard.KEY_RETURN || keyCode == Keyboard.KEY_NUMPADENTER) {
			actionPerformed(doneButton);
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		lockCodeTextField.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, I18n.format("lockablecontainers:lock.set_lock_code"), width / 2, 20, 0xffffff);
		drawString(fontRendererObj, I18n.format("lockablecontainers:lock.lock_code"), width / 2 - 150, 37, 0xa0a0a0);
		lockCodeTextField.drawTextBox();
		drawString(fontRendererObj, "", width / 2 - 150, 75 * fontRendererObj.FONT_HEIGHT, 0xa0a0a0);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}
