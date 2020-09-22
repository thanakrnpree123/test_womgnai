FROM node:12

WORKDIR /public

COPY package*.json ./

# install project dependencies
RUN npm install

COPY . .

EXPOSE 5000
CMD [ "node", "server" ]