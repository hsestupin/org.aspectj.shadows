package org.eclipse.jdt.internal.compiler.apt.dispatch;

import org.eclipse.jdt.internal.compiler.batch.CompilationUnit;

import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import java.io.*;
import java.net.URI;

/**
 * @author p.yushkovskiy
 */
public final class AjBatchFilerImpl extends BatchFilerImpl {

    public AjBatchFilerImpl(BaseAnnotationProcessorManager dispatchManager, BatchProcessingEnvImpl env) {
        super(dispatchManager, env);
    }

    @Override

    public FileObject createResource(JavaFileManager.Location location, CharSequence pkg, CharSequence relativeName, Element... originatingElements) throws IOException {
        final String name = String.valueOf(relativeName);
        final FileObject resource = super.createResource(location, pkg, relativeName, originatingElements);
        return name.endsWith(".aj") ? new HookedFileObject(resource) : resource;
    }

    private final class HookedFileObject implements FileObject {

        private final FileObject fileObject;
        private boolean _closed = false;

        HookedFileObject(FileObject fileObject) {
            this.fileObject = fileObject;
        }


        @Override
        public URI toUri() {
            return fileObject.toUri();
        }


        @Override
        public String getName() {
            return fileObject.getName();
        }


        @Override
        public InputStream openInputStream() throws IOException {
            return fileObject.openInputStream();
        }


        @Override
        public OutputStream openOutputStream() throws IOException {
            return new ForwardingOutputStream(fileObject.openOutputStream());
        }


        @Override
        public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
            return fileObject.openReader(ignoreEncodingErrors);
        }


        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return fileObject.getCharContent(ignoreEncodingErrors);
        }


        @Override
        public Writer openWriter() throws IOException {
            return new ForwardingWriter(fileObject.openWriter());
        }

        @Override
        public long getLastModified() {
            return fileObject.getLastModified();
        }

        @Override
        public boolean delete() {
            return fileObject.delete();
        }

        private void onClose() {
            if (_closed)
                return;
            _closed = true;
            final String name = fileObject.getName();
            final CompilationUnit unit = new CompilationUnit(null, name, null /* encoding */);
            addNewUnit(unit);
        }

        private final class ForwardingWriter extends Writer {

            private final Writer writer;

            public ForwardingWriter(Writer writer) {
                this.writer = writer;
            }

            @Override
            public void write(int c) throws IOException {
                writer.write(c);
            }

            @Override
            public void write(char[] cbuf) throws IOException {
                writer.write(cbuf);
            }

            @Override
            public void write(String str) throws IOException {
                writer.write(str);
            }

            @Override
            public void write(String str, int off, int len) throws IOException {
                writer.write(str, off, len);
            }

            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {
                writer.write(cbuf, off, len);
            }

            @Override
            public void flush() throws IOException {
                writer.flush();
            }

            @Override
            public void close() throws IOException {
                writer.close();
                onClose();
            }


            @Override
            public Writer append(CharSequence csq) throws IOException {
                return writer.append(csq);
            }


            @Override
            public Writer append(CharSequence csq, int start, int end) throws IOException {
                return writer.append(csq, start, end);
            }


            @Override
            public Writer append(char c) throws IOException {
                return writer.append(c);
            }
        }

        private final class ForwardingOutputStream extends OutputStream {

            private final OutputStream stream;

            public ForwardingOutputStream(OutputStream stream) {
                this.stream = stream;
            }

            @Override
            public void write(byte[] b) throws IOException {
                stream.write(b);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                stream.write(b, off, len);
            }

            @Override
            public void flush() throws IOException {
                stream.flush();
            }

            @Override
            public void close() throws IOException {
                stream.close();
                onClose();
            }

            @Override
            public void write(int b) throws IOException {
                stream.write(b);
            }
        }
    }
}