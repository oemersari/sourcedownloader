package oemersari.de.sourcedownlaoder;

import android.content.Context;
import android.os.AsyncTask;

public class SourceDownlaoder extends AsyncTask {

    private Context context;
    private Responce responce;
    private boolean isError;

    public interface Responce {
        void process(boolean _Error, String _Message, String _source);
    }

    public SourceDownlaoder(Context _context, Responce _responce) {
        this.responce = _responce;
        this.context = _context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        System.out.println("Starting");
        try {
            Thread.sleep(2000);
            isError = false;
        } catch (Exception e) {
            e.printStackTrace();
            isError = true;
        }
        System.out.println("Finish");
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        responce.process(isError, "MESSAGE", "SOURCE");
        super.onPostExecute(o);
    }
}
