
module.exports = {
  async rewrites() {
    return [{ source: '/api/proxy/:path*', destination: 'http://localhost:8080/api/:path*' }];
  },
};
