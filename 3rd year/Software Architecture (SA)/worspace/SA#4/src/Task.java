import org.junit.Test;

public class Task {
    private int psd, pcd;
    private int asd, acd;

    public Task() {
        psd = pcd = asd = acd = -1;
    }

    public int getPsd() {
        return psd;
    }

    public void setPsd(int psd) {
        if (psd <= this.pcd)
            this.psd = psd;
    }

    public int getPcd() {
        return pcd;
    }

    public void setPcd(int pcd) {
        if (this.psd <= pcd)
            this.pcd = pcd;
    }

    public int getAsd() {
        return asd;
    }

    public void setAsd(int asd) {
        if (asd < this.acd) {
            if (asd == -1)
                this.acd = -1;
            this.asd = asd;
        }
    }

    public int getAcd() {
        return acd;
    }

    public void setAcd(int acd) {
        if (this.asd != -1 && this.asd < acd)
            this.acd = acd;

    }


}