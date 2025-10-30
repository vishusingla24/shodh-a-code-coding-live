import Link from "next/link";
import Head from "next/head";

export default function Home() {
  const contests = [
    { id: "test", name: "Demo Contest", date: "Oct 31, 2025" },
    { id: "codequest", name: "CodeQuest 2025", date: "Nov 3, 2025" },
    { id: "bugsmash", name: "BugSmash Challenge", date: "Nov 10, 2025" },
  ];

  return (
    <>
      <Head>
        <title>Shodh Contest Platform</title>
      </Head>

      <div className="min-h-screen flex flex-col bg-gradient-to-br from-gray-50 to-blue-50 text-gray-800">
        {/* 🌟 Header */}
        <header className="bg-white shadow-md py-4 px-8 flex justify-between items-center sticky top-0 z-10">
          <h1 className="text-3xl font-bold text-blue-700 tracking-wide">
            Shodh Contest Platform
          </h1>
          <button className="px-5 py-2 bg-blue-600 text-white text-sm font-semibold rounded-lg shadow hover:bg-blue-700 transition-all">
            Login
          </button>
        </header>

        {/* 🧭 Main Content */}
        <main className="flex-grow container mx-auto py-14 px-6 text-center">
          <h2 className="text-2xl md:text-3xl font-semibold text-gray-800 mb-10">
            🚀 Upcoming Coding Contests
          </h2>

          <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-8 justify-center">
            {contests.map((contest) => (
              <div
                key={contest.id}
                className="p-8 bg-white rounded-2xl shadow-md hover:shadow-xl transition-transform hover:-translate-y-1"
              >
                <h3 className="text-lg font-bold text-blue-700 mb-2">
                  {contest.name}
                </h3>
                <p className="text-gray-500 text-sm mb-4">{contest.date}</p>
                <Link href={`/contest/${contest.id}`}>
                  <button className="w-full py-2 bg-gradient-to-r from-blue-600 to-indigo-600 text-white rounded-lg font-medium hover:opacity-90 transition-all">
                    View Contest
                  </button>
                </Link>
              </div>
            ))}
          </div>
        </main>

        {/* ⚙️ Footer */}
        <footer className="bg-white border-t py-4 text-center text-gray-600 text-sm">
          © {new Date().getFullYear()} Shodh Platform | Made with ❤️ by Shodh Team
        </footer>
      </div>
    </>
  );
}
