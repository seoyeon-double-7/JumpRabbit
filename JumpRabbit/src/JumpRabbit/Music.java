package JumpRabbit;

import javax.sound.sampled.*;
import java.io.File;

public class Music extends Thread{
	
	// 음악 실행할건지 판단
    boolean isLoop;
    
    //* 오디오 재생 관련 객체변수
    AudioInputStream stream; // 오디오 입력 스트림
    AudioFormat format; // 오디오 데이터 형식 세트(인코딩 작업)
    DataLine.Info info; // 데이터 라인의 오디오 정보 객체
    File bgm; // 파일 객체
    static Clip clip; // 오디오 클립

    // 음악 재생
    public Music(String name, boolean isLoop) {
        try {
            this.isLoop = isLoop; //음악 재생 유무 설정
            bgm = new File("music/"+name+".wav"); //음원 파일 가져오기
            stream = AudioSystem.getAudioInputStream(bgm); //파일과 스트림 연결
            format = stream.getFormat(); //stream의 format 가져오기
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream); //음악 실행
        } catch (Exception e) {
            System.out.println("bgm 실행 실패");
        }
    }

    public void close(){
        isLoop = false; //음악 재생 멈춤으로 값 설정
        clip.stop(); //음악 멈춤
        this.interrupt();
    }

    @Override
    public void run(){
        try{
            System.out.println(bgm.toString());
            if(isLoop){
                stream = AudioSystem.getAudioInputStream(bgm);
                format = stream.getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(stream);
                clip.loop(MAX_PRIORITY); //반복 재생
            }
            clip.start();
            System.out.println("음원 시작");
        }catch (Exception e){
            System.out.println("스레드 오류 발생");
            e.printStackTrace();
        }
    }


}
