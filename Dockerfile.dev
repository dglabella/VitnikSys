FROM eclipse-temurin:11-jdk-jammy

ENV JAVA_HOME=/opt/java/openjdk
ENV MAVEN_HOME=/usr/share/maven
ENV PATH="$MAVEN_HOME/bin:$PATH"

RUN apt-get update && apt-get install -y \
    curl unzip zip git maven \
    libgtk-3-0 \
    libx11-xcb1 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    libgl1 \
    libgl1-mesa-dri \
    libglx-mesa0 \
    libcanberra-gtk-module \
    libcanberra-gtk3-module \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /opt/javafx && \
    curl -L -o openjfx-sdk.zip https://download2.gluonhq.com/openjfx/13/openjfx-13_linux-x64_bin-sdk.zip && \
    unzip openjfx-sdk.zip -d /opt/javafx && \
    rm openjfx-sdk.zip

ENV JAVAFX_SDK=/opt/javafx/javafx-sdk-13
ENV PATH="/opt/javafx/javafx-sdk-13/bin:$PATH"

WORKDIR /app

CMD ["mvn", "javafx:run"]

