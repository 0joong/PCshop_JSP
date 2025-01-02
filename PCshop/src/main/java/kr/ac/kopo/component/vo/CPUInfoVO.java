package kr.ac.kopo.component.vo;

public class CPUInfoVO {
	
	private int itemCd;
    private String socket; // CPU 소켓
    private int core; // 코어 수
    private int thread; // 쓰레드 수
    private double baseClock; // 베이스 클럭 (GHz)
    private double boostClock; // 부스트 클럭 (GHz)
    
    public CPUInfoVO() {}
    
    
	public int getItemCd() {
		return itemCd;
	}


	public void setItemCd(int itemCd) {
		this.itemCd = itemCd;
	}


	public String getSocket() {
		return socket;
	}
	public void setSocket(String socket) {
		this.socket = socket;
	}
	public int getCore() {
		return core;
	}
	public void setCore(int core) {
		this.core = core;
	}
	public int getThread() {
		return thread;
	}
	public void setThread(int thread) {
		this.thread = thread;
	}
	public double getBaseClock() {
		return baseClock;
	}
	public void setBaseClock(double baseClock) {
		this.baseClock = baseClock;
	}
	public double getBoostClock() {
		return boostClock;
	}
	public void setBoostClock(double boostClock) {
		this.boostClock = boostClock;
	}
	@Override
	public String toString() {
		return "CPUInfoVO [socket=" + socket + ", core=" + core + ", thread=" + thread + ", baseClock=" + baseClock
				+ ", boostClock=" + boostClock + "]";
	}
    
    
    

    // 필요시 추가 메서드 작성 가능
}