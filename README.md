# Source Downloader

maven { url "https://jitpack.io" }

implementation 'com.github.oemersari:sourcedownloader:master'


new SourceDownloader("http://www.google.com", new SourceDownloader.Responce() {

            @Override
            public void onFinished(String _source) {
                
            }

            @Override
            public void onError(String _ErrorMessage) {

            }
        }).execute();
