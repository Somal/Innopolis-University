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
        if (psd <= this.pcd && (psd > 0 || psd == -1))
            this.psd = psd;
    }

    public int getPcd() {
        return pcd;
    }

    public void setPcd(int pcd) {
        if (this.psd <= pcd && (pcd > 0 || pcd == -1))
            this.pcd = pcd;
    }

    public int getAsd() {
        return asd;
    }

    public void setAsd(int asd) {
        if (asd == -1)
            this.acd = this.asd = -1;
        else if (asd < this.acd && asd>0) {
            this.asd = asd;
        }
    }

    public int getAcd() {
        return acd;
    }

    public void setAcd(int acd) {
        if (acd == -1)
            this.acd = -1;
        else if (this.asd != -1 && this.asd < acd && acd > 0)
            this.acd = acd;

    }


}