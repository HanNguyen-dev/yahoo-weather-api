# base
FROM node:13.12.0-alpine as base
WORKDIR /reactapp
COPY package*.json ./
RUN npm install

# build
FROM base as build
COPY . .
ARG URL
ENV REACT_APP_BASE_URL ${URL}
RUN npm run build

# deploy
FROM nginx:stable-alpine as deploy
COPY --from=build /reactapp/build /usr/share/nginx/html
RUN apk --no-cache add gettext
COPY ./nginx.conf /etc/nginx/nginxconf.template
CMD ["/bin/sh", "-c", "envsubst < /etc/nginx/nginx.template > /etc/nginx/conf.d/default.conf && nginx -g 'daemon off;'"]