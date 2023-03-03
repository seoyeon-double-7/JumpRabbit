package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import JumpRabbit.JumpRabbit;
import dialog.ConfirmDialog;

public class Main {
	//* 윈도우창 가로 세로 폭
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 830;

	//* 기본 색
	public static final Color defaultColor = Color.decode("#ff42a5");

	//* 폰트
	public static Font font;

	public static void main(String[] args) throws SQLException {
		// 폰트 적용
		font = new Font("Neo둥근모", Font.BOLD, 50);

		// 윈도우창 실행
		new JumpRabbit();
	}

}