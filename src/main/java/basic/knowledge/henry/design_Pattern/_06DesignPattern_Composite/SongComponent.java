package basic.knowledge.henry.design_Pattern._06DesignPattern_Composite;

public abstract class SongComponent {
    public void add(SongComponent newSongCompoent) {
        throw new UnsupportedOperationException();
    }

    public void remove(SongComponent newSongCompoent) {
        throw new UnsupportedOperationException();
    }

    public SongComponent getComponent(int componentIndex) {
        throw new UnsupportedOperationException();
    }

    public String getSongName() {
        throw new UnsupportedOperationException();
    }

    public String getBandName() {
        throw new UnsupportedOperationException();
    }

    public int getReleaseYear() {
        throw new UnsupportedOperationException();
    }

    public void displaySongInfo() {
        throw new UnsupportedOperationException();
    }

}
